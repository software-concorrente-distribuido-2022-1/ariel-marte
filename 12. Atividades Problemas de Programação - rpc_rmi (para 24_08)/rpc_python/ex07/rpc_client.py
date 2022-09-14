import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://127.0.0.1:4000/")
    
idade = int(input("Idade: "))
tempoServico = int(input("Tempo de servico: "))

resposta = proxy.podeAposentar(idade,tempoServico)

print("Pode aposentar" if resposta else "Nao pode aposentar")