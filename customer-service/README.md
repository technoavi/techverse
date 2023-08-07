#to list down docker image
docker image ls  
# build docker image 
docker build -t customer-service-img .  
#to run docker image
docker run -p 9091:9091 -t customer-service-img

#Mapping port on the host (9091 left) to the port inside Docker (9091)right

#to remove image/container

docker rmi 25d0f5e5d6da -f  
