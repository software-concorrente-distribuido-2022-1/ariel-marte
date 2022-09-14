from xmlrpc.server import SimpleXMLRPCServer

def foiAprovado(n1 : float, n2 : float, n3 : float):
    media = float()
    media = (n1+n2)/2
    if(media >= 7):
        return True
    if(media <= 3):
        return False
    
    media = (media+n3)/2
    
    return media >= 5

server = SimpleXMLRPCServer(("127.0.0.1", 4000))
print("Listening on port 4000...")
server.register_function(foiAprovado, 'foiAprovado')

server.serve_forever()