import socket

HOST = "localhost"  
PORT = 8080  

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

nome = str(input("Nome: "))
sexo = str(input("Sexo: (F/M): "))
idade = int(input("Idade: "))

req = nome + "|" + sexo + "|" + str(idade) + "\n"

s.sendall(req.encode("utf-8"))
data = s.recv(1024)

print(data.decode("utf-8"))