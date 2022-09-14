from xmlrpc.server import SimpleXMLRPCServer

def podeAposentar(idade : int, tempoServico : int):
    return (idade >= 65) or (tempoServico >= 30) or (idade >= 60 and tempoServico >= 25)

server = SimpleXMLRPCServer(("127.0.0.1", 4000))
print("Listening on port 4000...")
server.register_function(podeAposentar, 'podeAposentar')

server.serve_forever()