import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://127.0.0.1:4000/")
    
sexo = str(input("Sexo (F/M): "))
altura = float(input("Altura (9.99m): "))

resposta = proxy.calculaPeso(sexo, altura)

print(resposta)