from django.db import models

class Modelo(models.Model):
    nome = models.CharField(max_length=100)
    descricao = models.TextField()
    arquivo = models.FileField(upload_to='/modelos')

    class Meta:
        db_table = 'modelo'
