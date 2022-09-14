import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://127.0.0.1:4000/")
    
n1 = float(input("Nota 1 (10.00): "))
n2 = float(input("Nota 2 (10.00): "))
n3 = float(input("Nota 3 (10.00): "))

resposta = proxy.foiAprovado(n1, n2, n3)

print("Aprovado" if resposta else "Reprovado")