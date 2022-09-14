from xmlrpc.server import SimpleXMLRPCServer

def ehMaiorIdade(sexo : str, idade : int) :
    return (sexo == "F" and idade >= 21) or (sexo == "M" and idade >= 18)

server = SimpleXMLRPCServer(("127.0.0.1", 4000))
print("Listening on port 4000...")
server.register_function(ehMaiorIdade, 'ehMaiorIdade')

server.serve_forever()