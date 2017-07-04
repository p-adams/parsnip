from flask import Flask
from flask_restful import Resource, Api
import nltk
sentence = "Hello from Python server"
sen_tok = nltk.word_tokenize(sentence)

app = Flask(__name__)
api = Api(app)

class Test(Resource):
    def get(self):
        return {'greetings': sen_tok}

api.add_resource(Test, '/api')

if __name__ == '__main__':
    app.run(debug=True)
