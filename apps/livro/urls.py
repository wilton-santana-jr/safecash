from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# LIVRO
urlpatterns = patterns('apps.livro.views',
    (r'^$', 'home'))
