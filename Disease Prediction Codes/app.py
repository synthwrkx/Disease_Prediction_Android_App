from flask import Flask, request, jsonify
import pickle
import json
import pandas as pd
import numpy as np
from scipy.stats import mode
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.svm import SVC
from sklearn.naive_bayes import GaussianNB
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score, confusion_matrix
import warnings

warnings.filterwarnings('ignore')
#%matplotlib inline

data = pd.read_csv('Training.csv').dropna(axis = 1)
X = data.iloc[:,:-1]

pkl_model_nb = pickle.load(open('nb.pkl','rb'))
pkl_model_rf = pickle.load(open('rf.pkl','rb'))
pkl_model_svm = pickle.load(open('svm.pkl','rb'))

symptoms = X.columns.values

encoder = LabelEncoder()
data["prognosis"] = encoder.fit_transform(data["prognosis"])

# Creating a symptom index dictionary to encode the
# input symptoms into numerical form
symptom_index = {}
for index, value in enumerate(symptoms):
    symptom = " ".join([i.capitalize() for i in value.split("_")])
    symptom_index[symptom] = index

data_dict = {
    "symptom_index":symptom_index,
    "predictions_classes":encoder.classes_
}

def predictDisease(symptoms):
    symptoms = symptoms.split(",")
    
    # creating input data for the models
    input_data = [0] * len(data_dict["symptom_index"])
    for symptom in symptoms:
        index = data_dict["symptom_index"][symptom]
        input_data[index] = 1
        
    # reshaping the input data and converting it
    # into suitable format for model predictions
    input_data = np.array(input_data).reshape(1,-1)
    
    # generating individual outputs
    rf_prediction = data_dict["predictions_classes"][pkl_model_rf.predict(input_data)[0]]
    nb_prediction = data_dict["predictions_classes"][pkl_model_nb.predict(input_data)[0]]
    svm_prediction = data_dict["predictions_classes"][pkl_model_svm.predict(input_data)[0]]
    
    # making final prediction by taking mode of all predictions
    if(rf_prediction!=nb_prediction and nb_prediction!=svm_prediction and rf_prediction!=svm_prediction):
        final_prediction = rf_prediction
    else:
        final_prediction = mode([rf_prediction, nb_prediction, svm_prediction])[0][0]
    predictions = {
        #"rf_model_prediction": rf_prediction,
        #"naive_bayes_prediction": nb_prediction,
        #"svm_model_prediction": svm_prediction,
        "final_prediction":final_prediction
    }
    print(predictions)
    return predictions

# Testing the function
#print(predictDisease("Itching,Skin Rash,Nodal Skin Eruptions"))

app = Flask(__name__)

@app.route("/", methods=['POST','GET'])
def get_symptoms():
    symptoms = request.get_json()
    #symptoms = {'symptom':[{'symp_name':'Itching'},{'symp_name':'Skin Rash'},{'symp_name':'Nodal Skin Eruptions'}]}
    temp_lst = symptoms['symptom']
    print(temp_lst)
    lst = []
    for i in temp_lst :
        lst.append(i['symp_name'])
    symps = ','.join(lst)
    result = predictDisease(symps)

    response = jsonify(result)
    response.status_code = 200

    return response

if __name__ == '__main__':
    portno = 5000
    app.run(host='0.0.0.0', port=portno)
