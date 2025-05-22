import json
import os

def read_json_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        data = json.load(file)
    return data

def write_json_file(data, file_path):
    with open(file_path, 'w', encoding='utf-8') as file:
        json.dump(data, file, ensure_ascii=False, indent=4)

def main():
    input_file = 'POI/beijing_poi_attractions.json'
    output_dir = 'POI/area_files_attrations/'

    # Create the output directory if it doesn't exist
    os.makedirs(output_dir, exist_ok=True)

    # Read the JSON data from the input file
    poi_data = read_json_file(input_file)

    # Initialize a dictionary to store data by area
    area_data = {}

    # Group data by area
    for poi in poi_data:
        area = poi['area']
        if area not in area_data:
            area_data[area] = []
        area_data[area].append(poi)

    # Write data to separate files based on area
    for area, data_list in area_data.items():
        output_file = os.path.join(output_dir, f'{area}_restaurants.json')
        write_json_file(data_list, output_file)
        print(f'Saved {len(data_list)} restaurants from {area} to {output_file}')

if __name__ == '__main__':
    main()
