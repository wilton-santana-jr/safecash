from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# RELATORIO
urlpatterns = patterns('apps.relatorio.views',
    (r'^$', 'home'))
