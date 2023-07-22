import logging
import os

if not os.path.exists('./data/entries'):
    os.makedirs('./data/entries')


class Constants:
    FORMATTER = logging.Formatter('[%(asctime)s][%(name)s][%(levelname)s]: %(message)s')

    FILE_HANDLER = logging.FileHandler('./data/logs.log')
    FILE_HANDLER.setLevel(logging.INFO)
    FILE_HANDLER.setFormatter(FORMATTER)

    STREAM_HANDLER = logging.StreamHandler()
    STREAM_HANDLER.setLevel(logging.DEBUG)
    STREAM_HANDLER.setFormatter(FORMATTER)

    LOG_PATH = './data/logs.log'
    LOG_LEVEL = logging.DEBUG

    CONFIRMED_CASES_PATH = './data/confirmed_cases.txt'
    ENTRIES_PATH = './data/entries'
    LOCATIONS_PATH = './data/locations.txt'


def generate_suggestions(file_path):
    try:
        with open(file_path, 'r') as file:
            lines = file.readlines()
            suggestions = [line.strip() for line in lines]
        return suggestions
    except FileNotFoundError:
        return []
