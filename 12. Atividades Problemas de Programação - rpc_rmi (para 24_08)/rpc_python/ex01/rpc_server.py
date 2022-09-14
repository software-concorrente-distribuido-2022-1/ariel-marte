from xmlrpc.server import SimpleXMLRPCServer

def calcularReajuste(cargo : str, salario : float):
    novoSalario = float()
    if(cargo == "operador"):
        novoSalario = salario + ((salario*20)/100)
    
    if(cargo == "programador"):
        novoSalario = salario + ((salario*18)/100)

    print(novoSalario)

    return novoSalario

server = SimpleXMLRPCServer(("127.0.0.1", 4000))
print("Listening on port 4000...")
server.register_function(calcularReajuste, 'calcularReajuste')

server.serve_forever()