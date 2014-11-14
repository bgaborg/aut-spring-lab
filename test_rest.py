from flask import Flask, jsonify, json, request

app = Flask(__name__)

@app.route('/hello')
def index():
    return "Hello, World!"
	
@app.route('/phones/addMetrics', methods = ['POST'])
def api_message():
	msg =  "JSON Message: " + json.dumps(request.json)
	
	with open("test.json", "a") as f:
		f.write(json.dumps(request.json) + '\n')	
	
	print msg
	return "Data received."
	
if __name__ == '__main__':
    app.run(debug = True, host='0.0.0.0')