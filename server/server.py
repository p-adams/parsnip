from flask import Flask
from flask_restful import Resource, Api, reqparse
import nltk
sentence = "Hello from Python server"
sen_tok = nltk.word_tokenize(sentence)

app = Flask(__name__)
api = Api(app)

parser = reqparse.RequestParser()
parser.add_argument('nlp_data')

class NLProcessor(Resource):
    def get(self):
        return {'greetings': sen_tok}
    
    def post(self):
        args = parser.parse_args()
        print(args['nlp_data'])

api.add_resource(NLProcessor, '/api')

if __name__ == '__main__':
    app.run(debug=True)
