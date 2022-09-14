import socket

HOST = "localhost"  
PORT = 8080  

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

idade = int(input("Idade: "))
tempoServ = int(input("Tempo de servico: "))

req = str(idade) + "|" + str(tempoServ) + "\n"

s.sendall(req.encode("utf-8"))
data = s.recv(1024)

print(data.decode("utf-8"))