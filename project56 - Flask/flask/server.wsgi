#!venv/bin/python

import sys
import logging

logging.basicConfig(stream=sys.stderr)
sys.path.insert(0, '/var/www/project56/flask')
from app import app as application
application.debug = True