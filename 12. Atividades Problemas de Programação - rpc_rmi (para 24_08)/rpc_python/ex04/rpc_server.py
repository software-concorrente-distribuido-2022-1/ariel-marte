from xmlrpc.server import SimpleXMLRPCServer

def calculaPeso(sexo : str, altura : float):
    pesoIdeal = float()
    if(sexo == "F"):
        pesoIdeal = (62.1*altura)-44.7
    if(sexo == "M"):
        pesoIdeal = (72.7*altura)-58
    
    return pesoIdeal

server = SimpleXMLRPCServer(("127.0.0.1", 4000))
print("Listening on port 4000...")
server.register_function(calculaPeso, 'calculaPeso')

server.serve_forever()