# AWAN-App
This app is a part of Bangkit 2022 Capstone Project from team C22-PS272

## Background
Weather forecasts have evolved a lot to this day. By knowing the weather changes that will occur then people can prepare themselves to face the impact of these weather changes. We wanted to make an application to predict the weather so that people can prepare for the impact of sudden weather changes so it doesn't interfere with their activities.
According to Ivan Nugroho's research in 2014 with the title “Aplikasi Prakiraan Cuaca dan Intensitas Curah Hujan Menggunakan Android”, it is explained that people need information about weather conditions to facilitate their daily activities. In his research, it is also explained that there is one drawback, namely because the database is online, an internet network connection is needed to find out information from weather forecasts and rainfall intensity. Therefore, we try to make it offline so that it can be accessed even without the internet. We will use the Design Thinking method to make our plan to be systematic.

## Deployment to Google Cloud Platform
These are step to deploy Machie Learning model using Google Compute Engine

1. Create Server 
   Google Compute Engine is used to make our own server.
   Navigation Menu > Google Compute Engine > VM Instance
2. Create Firewall
   Firewall is used to connect the tcp port that we have set in host.
   Navigation Menu > VPC Network > Firewall 
3. Run command in SSH
   This command lines is used to interact with our server.
   
   - Run this command to update
        
          sudo apt update
          
   - Clone the github that have REST to connect
   
          git clone https://github.com/iif8/cpb22-awan.git
          
   - If the step above does not work, it is because the VM is still new. Run this command
   
          sudo apt install git
          
   - Download and install minoconda to get python environment
   
          wget https://repo.anaconda.com/miniconda/Miniconda3-4.7.10-Linux-x86_64.sh
          bash Miniconda3-4.7.10-Linux-x86_64.sh
          
   - If the step above does not work, it is because the VM is still new. Run this command
   
          sudo apt install sudo
          
   - Point to your path, confirm the instalation, create, and activate the environment
   
          export PATH=/home/<your folder name>/miniconda3/bin:$PATH
          which conda
          conda create -n cpb22-awan python=3.7
          conda activate cpb22-awan
          conda init
          
   - Get in to your repo to know the requirements.txt. 
   
          cd cpb22-awan
          pip freeze > requirements.txt
          
   - Instal library that needed. The vm will provide instructions for libraries that need to be installed
   
          pip install flask
          pip install tensorflow
          pip install numphy
          pip install image
          
   -  Make new folder and upload the Machine Learning model that has extension (.h5). The file is uploaded manually from setting in VM SSH.
   
          mkdir model
    
          

