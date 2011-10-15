from django.db import models

class Projeto(models.Model):
    nome = models.CharField(max_length=100)
    responsavel = models.CharField(max_length=100)
    valor = models.FloatField(default=0)
    descricao = models.TextField()
    data_inicio = models.DateField()
    data_fim = models.DateField()

    class Meta:
        db_table = 'projeto'
