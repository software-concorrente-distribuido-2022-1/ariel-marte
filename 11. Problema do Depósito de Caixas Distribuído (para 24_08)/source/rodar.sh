#!/bin/bash
java ServidorSimples 8080 &
for i in {1..5}
do
	java ClienteConsumidor localhost 8080  &
	java ClienteProdutor localhost 8080  &
done
