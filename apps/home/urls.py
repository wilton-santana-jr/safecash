from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# HOME
urlpatterns = patterns('apps.home.views',
    (r'^home/$', 'home'),
    (r'^login/$', 'login'))
