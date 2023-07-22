import logging
import os
from typing import List

if not os.path.exists('./data'):
    os.makedirs('./data/entries')
    os.makedirs('./data/qrcodes')


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
    QR_CODES_PATH = './data/qrcodes'


def generate_suggestions(path: str) -> List[str]:
    try:
        if os.path.isfile(path):
            with open(path, 'r') as file:
                lines = file.readlines()
                suggestions = [line.strip() for line in lines]
            return suggestions

        suggestions = []
        for item in os.listdir(path):
            item_path = os.path.join(path, item)

            # If item is a file, appends to suggestions list
            if os.path.isfile(item_path):
                suggestions.append(' '.join([word.capitalize() for word in item.split('-')]))

        return suggestions

    except FileNotFoundError:
        return []
