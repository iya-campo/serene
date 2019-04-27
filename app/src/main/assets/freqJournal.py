import sys
import codecs
import nltk
from nltk.corpus import stopwords
from firebase import firebase
from nltk import FreqDist, bigrams
import smtplib
from matplotlib import pyplot as plt
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.image import MIMEImage


default_stopwords = set(nltk.corpus.stopwords.words('english'))

firebase = firebase.FirebaseApplication("https://serene-5fe13.firebaseio.com/")

x = int(1)
y = int(1)
wordsForAWeek = ''
Week = int(1)
boolSendEmail = 'no'
UserID = firebase.get("CurrentUserID", None)

for i in range(0, 8):
    if(i == 8):
        i == 0
        Week += 1
        wordsForAWeek = ''
    else:
        kek = (y+i) 
        result = firebase.get('/Journal/' + str(UserID) +'/' + str(kek), 'content')
        if(result != 'None'):
            wordsForAWeek += result

                        
def createGraph():
    image = 'graph.png'
    words = nltk.word_tokenize(wordsForAWeek)
    words = [word for word in words if len(word) > 1]
    words = [word for word in words if not word.isnumeric()]
    words = [word.lower() for word in words]
    words = [word for word in words if word not in default_stopwords]
    fdist = nltk.FreqDist(words)

    plt.ion()
    fdist.plot(10, title='Top 10 Most Common Words in Corpus')
    plt.savefig(image)
    plt.ioff()
    plt.show()

    for word, frequency in fdist.most_common(10):
            print(u'{};{}'.format(word, frequency))
            resultPut = firebase.put('/FreqWords/' + str(UserID) + '/' + str(Week), word, frequency)

    return image
    
def SendEmail(image):
    gmail_user = 'assassino516@gmail.com'  
    gmail_password = 'lordelithescrub'

    strFrom = gmail_user
    strTo = 'ealcipriano@donbosco.edu.ph'

    msgRoot = MIMEMultipart('related')
    msgRoot['Subject'] = 'Weekly Report'
    msgRoot['From'] = strFrom
    msgRoot['To'] = strTo
    msgRoot.preamble = 'This is a multi-part message in MIME format.'

    fp = open(image, 'rb')
    msgImage = MIMEImage(fp.read())
    fp.close()

    # Define the image's ID as referenced above
    msgImage.add_header('Content-ID', '<image1>')
    msgRoot.attach(msgImage)

    server = smtplib.SMTP_SSL('smtp.gmail.com', 465)
    server.ehlo()
    server.login(gmail_user, gmail_password)
    server.sendmail(strFrom, strTo, msgRoot.as_string())
    server.close()

if(wordsForAWeek != ''):
    SendEmail(createGraph()) 
