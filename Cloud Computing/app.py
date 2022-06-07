import flask
import io
import string
import time
import os
import numpy as np
import tensorflow as tf
from PIL import Image
from flask import Flask, jsonify, request

app = Flask(__name__)

model = tf.keras.models.load_model('cloud.h5')

def prepare_image(img):
    img = Image.open(io.BytesIO(img))
    img = img.resize((224, 224))
    img = np.array(img)
    img = np.expand_dims(img, 0)
    return img

def process(model, img):
    predictions = model.predict(img)

    class_names = ['Heavy_Rain', 'Low_Chance_of_Rain', 'Medium_Rain']

    predicted_class = class_names[np.argmax(predictions[0])]
    confidence = round(100 * (np.max(predictions[0])), 2)
    return predicted_class, confidence

@app.route('/')
def index():
    return {'message': 'server is running'}, 200

@app.route('/predict', methods=["POST"])
def predict():
    if 'file' not in request.files:
        return "Please try again. The Image doesn't exist"
    
    file = request.files.get('file')

    if not file:
        return
    
    img_bytes = file.read()
    img = prepare_image(img_bytes)

    prediction, confidence = process(model, img)

    return jsonify(prediction, confidence)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
