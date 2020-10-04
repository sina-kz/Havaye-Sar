import base64

from django.db import models


# Create your models here.

class Photo(models.Model):
    content = models.CharField(max_length=1000000)

    def __str__(self):
        base64_bytes = self.content.encode('ascii')
        message_bytes = base64.b64decode(base64_bytes)
        message = message_bytes.decode('ascii')

        print(message)
        return self.content
