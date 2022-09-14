import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://127.0.0.1:4000/")
    
nome = str(input("Nome: "))
cargo = str(input("Cargo: "))
salario = float(input("Salario: (999.99)"))

resposta = proxy.calcularReajuste(cargo,salario)

print(resposta)