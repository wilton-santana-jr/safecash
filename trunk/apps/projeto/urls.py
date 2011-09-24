from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# PROJETO
urlpatterns = patterns('apps.projeto.views',
    (r'^$', 'home'))
