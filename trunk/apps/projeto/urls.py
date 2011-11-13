from django.conf.urls.defaults import *
#from projeto.models import Projeto
# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# PROJETO
urlpatterns = patterns('apps.projeto.views',
    (r'^$', 'projeto'),
    (r'^cadatro_projeto_ajax', 'cadatro_projeto_ajax')
)
