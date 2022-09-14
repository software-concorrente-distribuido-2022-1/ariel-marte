from xmlrpc.server import SimpleXMLRPCServer

def getClassificacao(idade: int):
    if (idade >= 5 and idade <= 7):
        return "Infantil A"
    
    if (idade >= 9 and idade <= 10):
        return "Infantil B"
    
    if (idade >= 11 and idade <= 13):
        return "Juvenil A"
    
    if (idade >= 14 and idade <= 17):
        return "Juvenil B"
    
    if (idade >= 18):
        return "Adulto"
    
    return "Idade inválida"

server = SimpleXMLRPCServer(("127.0.0.1", 4000))
print("Listening on port 4000...")
server.register_function(getClassificacao, 'getClassificacao')

server.serve_forever()