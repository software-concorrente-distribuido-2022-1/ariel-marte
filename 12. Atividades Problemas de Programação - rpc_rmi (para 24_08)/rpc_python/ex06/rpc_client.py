import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://127.0.0.1:4000/")
    
nome = str(input("Nome: "))
nivel = str(input("Nivel: "))
salario = float(input("Salario: (999.99)"))
numDep = int(input("Numero de dependentes: "))

resposta = proxy.calculaSalarioLiquido(nivel, salario, numDep)

print("Nome: " + nome + "; Nivel: " + nivel + "; Salario Liquido: " + "{:.2f}".format(resposta) )