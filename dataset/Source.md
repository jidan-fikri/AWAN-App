# Dataset Description  
This project used the dataset from Cirrus Cumulus Stratus Nimbus (CSSN) database: *https://dataverse.harvard.edu/dataset.xhtml?persistentId=doi:10.7910/DVN/CADDPD*  

Originally, this dataset contains 2543 cloud images that divided into 11 categories:  
1. Ci - Cirrus, image count: 139
2. Cs - Cirrostratus, image count: 287
3. Cc - Cirrocumulus, image count: 268
4. Ac - Altocumulus, image count: 221
5. As - Altostratus, image count: 188
6. Cu - Cumulus, image count: 182
7. Cb - Cumulonimbus, image count: 242
8. Ns - Nimbostratus, image count: 274
9. Sc - Stratocumulus, image count: 340
10. St - Stratus, image count: 202
11. Ct - Contrails, image count: 200

Since contrails cloud formed by human causes (aircraft engine exhaust), so we do not include that class into the dataset, plus we cleaned some of the image from the dataset that may be a problem for our model such as images that are poorly captured. So in the end, we only use 2336 total of cloud images from the original dataset.  
