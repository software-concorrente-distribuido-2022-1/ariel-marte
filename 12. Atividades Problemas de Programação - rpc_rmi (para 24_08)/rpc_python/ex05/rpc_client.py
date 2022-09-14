import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://127.0.0.1:4000/")
    
idade = int(input("Idade: "))

resposta = proxy.getClassificacao(idade)

print(resposta)