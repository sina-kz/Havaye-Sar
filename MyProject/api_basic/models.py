from django.db import models


# Create your models here.

class Photo(models.Model):
    content = models.CharField(max_length=10000)

    def __str__(self):
        return self.content
