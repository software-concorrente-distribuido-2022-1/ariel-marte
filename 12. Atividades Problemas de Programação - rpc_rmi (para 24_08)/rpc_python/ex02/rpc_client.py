import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://127.0.0.1:4000/")
    
nome = str(input("Nome: "))
sexo = str(input("Sexo: "))
idade = int(input("Idade: "))

resposta = proxy.ehMaiorIdade(sexo,idade)

print("Atingiu a maioridade" if resposta else "Nao atingiu a maioridade")