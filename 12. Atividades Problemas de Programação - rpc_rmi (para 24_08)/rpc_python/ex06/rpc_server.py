from xmlrpc.server import SimpleXMLRPCServer

def calculaSalarioLiquido(nivel : str, salario : float, numDependentes: int):
    salarioLiquido = float()
    temDependente = numDependentes > 0
    desconto = int()
    
    if (nivel == "A"):
        desconto = 8 if temDependente else 3
    elif (nivel == "B"):
        desconto = 10 if temDependente else 5
    elif (nivel == "C"):
        desconto = 15 if temDependente else 8
    elif (nivel == "D"):
        desconto =  17 if temDependente else 10
    
    descontoTotal = salario*(desconto/100)
    salarioLiquido = salario-descontoTotal
    return salarioLiquido

server = SimpleXMLRPCServer(("127.0.0.1", 4000))
print("Listening on port 4000...")
server.register_function(calculaSalarioLiquido, 'calculaSalarioLiquido')

server.serve_forever()