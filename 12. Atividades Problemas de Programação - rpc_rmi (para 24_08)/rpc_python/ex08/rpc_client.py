import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://127.0.0.1:4000/")

saldoMedio = float(input("Saldo medio: (999.99)"))

resposta = proxy.calcularValorCrediro(saldoMedio)

print(resposta)