<p align="center">
  <img width="300" src="https://github.com/jidan-fikri/AWAN-App/blob/master/Assets/Awan_Logo-1_1__1_-removebg-preview.png">
</p>
<h1 align="center">AWAN: Application on Weather Analysis Through Cloud Images Classification</h1>

**[Demo App](https://drive.google.com/file/d/11cGNiyM7-L1fp5DLZrRyULXxIPt3Rh4G/view?usp=sharing) - [Download App](https://drive.google.com/file/d/1Y4d-aQ7V_emExz7EuUUEmMpTsPSCLb2P/view?usp=sharing) - [Product Orientation](https://docs.google.com/presentation/d/1IB50x-IChrVnMhXfSvF2GXmtLoCozh-k/edit#slide=id.p1)**
  
This app is a part of Bangkit 2022 Capstone Project from team C22-PS272 (Krizaryh):

- (ML) M7119F1498 - Jidan Fikri - Institut Teknologi Sumatera
- (ML) M2119F1499 - Miftahul Donny Sanjaya - Institut Teknologi Sumatera
- (ML) M7012G1294 - Kurnia Mayestic Gulo - Universitas Telkom
- (CC) C2186F1797 - Aqmal Idris Zarkasih  - Universitas Bakrie
- (CC) C2299F2586 - Iffah Fadhilah  - Universitas Pendidikan Indonesia
- (MD) A7004G0351 - Aldi Fahmi Sihotang - Institut Teknologi Sepuluh Nopember (Inactive)  


## Background
Weather forecasts have evolved a lot to this day. By knowing the weather changes that will occur then people can prepare themselves to face the impact of these weather changes. We wanted to make an application to predict the weather so that people can prepare for the impact of sudden weather changes so it doesn't interfere with their activities.
According to Ivan Nugroho's research in 2014 with the title “Aplikasi Prakiraan Cuaca dan Intensitas Curah Hujan Menggunakan Android”, it is explained that people need information about weather conditions to facilitate their daily activities. In his research, it is also explained that there is one drawback, namely because the database is online, an internet network connection is needed to find out information from weather forecasts and rainfall intensity. Therefore, we try to make it offline so that it can be accessed even without the internet. We will use the Design Thinking method to make our plan to be systematic.

## Advantages
The advantages of our capstone project
1. Personally, it can help to predict weather conditions from cloud that we take the picture
2. For workers field, they can work faster or finish the work before rain arrives.

## User Interface and User Experience Application
This is User Interview of AWAN app. This design is adapted to the results of UX research. The UI/UX design is done using the design thinking method in the figma application. The following is the overall result of a series of design thinking methods [UI/UX Design](https://tinyurl.com/PlanAPKinFigma). 

| Loading Page  | Login Page | Register Page | Home Page |
| ------------- | ------------- | ------------- | ------------- |
|<img src="https://github.com/jidan-fikri/AWAN-App/blob/master/Assets/Load_AWAN.jpg" width="1000"/>|<img src="https://github.com/jidan-fikri/AWAN-App/blob/master/Assets/Login_AWAN.jpg" width="1100"/>|<img src="https://github.com/jidan-fikri/AWAN-App/blob/master/Assets/Register_AWAN.jpg" width="1000"/>|<img src="https://github.com/jidan-fikri/AWAN-App/blob/master/Assets/Home_AWAN.jpg" width="1100"/>|

| History Page  | Notification Page| Predict Page | 
| ------------- | ------------- | ------------- | 
|<img src="https://github.com/jidan-fikri/AWAN-App/blob/master/Assets/History_AWAN.jpg" width="1000"/>|<img src="https://github.com/jidan-fikri/AWAN-App/blob/master/Assets/Notifications_AWAN.jpg" width="900"/>|<img src="https://github.com/jidan-fikri/AWAN-App/blob/master/Assets/Detection_AWAN.jpg" width="1000"/>| 

| Prediction_1 Page  | Prediction_2 Page| Account Page | 
| ------------- | ------------- | ------------- | 
|<img src="https://github.com/jidan-fikri/AWAN-App/blob/master/Assets/Detection1_AWAN.jpg" width="900"/>|<img src="https://github.com/jidan-fikri/AWAN-App/blob/master/Assets/Detection2_AWAN.jpg" width="900"/>|<img src="https://github.com/jidan-fikri/AWAN-App/blob/master/Assets/Account_AWAN.jpg" width="980"/>| 


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
   
   - Run this command to update.
        
          sudo apt update
          
   - Clone the github that have REST to connect
   
          git clone https://github.com/iif8/cpb22-awan.git
          
   - If the step above does not work, it is because the VM is still new. Run this command.
   
          sudo apt install git
          
   - Download and install minoconda to get python environment.
   
          wget https://repo.anaconda.com/miniconda/Miniconda3-4.7.10-Linux-x86_64.sh
          bash Miniconda3-4.7.10-Linux-x86_64.sh
          
   - If the step above does not work, it is because the VM is still new. Run this command.
   
          sudo apt install wget
          
   - Point to your path, confirm the instalation, create, and activate the environment.
   
          export PATH=/home/<your folder name>/miniconda3/bin:$PATH
          which conda
          conda create -n cpb22-awan python=3.7
          conda activate cpb22-awan
          conda init
          
   - Get in to your repository to know the requirements.txt. 
   
          cd cpb22-awan
          pip freeze > requirements.txt
          
   - Instal library that needed. The vm will provide instructions for libraries that need to be installed.
   
          pip install flask
          pip install tensorflow
          pip install numphy
          pip install image
          
   - If library for image cannot be downloaded, run this command.
   
         python3 -m pip install --upgrade pip
         python3 -m pip install --upgrade Pillow
          
   -  Make new folder and upload the Machine Learning model that has extension (.h5). The file is uploaded manually from setting in VM SSH.
   
          mkdir model
          
   - If there is problem in file app.py, change the error with command below. Then run the server.
   
         sudo nano app.py
         python3 app.py
         
  4. Test the server with open the external IP. Then test the prediction with Postman. The result is below. 
    
      ![Testing awan](https://user-images.githubusercontent.com/99232109/172502561-7fcf89da-73ce-42b3-b00f-2ed70c1c6bf5.png)
 
 Thank you for visiting. Happy testing! ✨
  
  
 

    
          

