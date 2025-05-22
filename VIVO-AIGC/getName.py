import json

def read_json_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        data = json.load(file)
    return data

def write_json_file(data, file_path):
    with open(file_path, 'w', encoding='utf-8') as file:
        json.dump(data, file, ensure_ascii=False, indent=4)

def extract_names(poi_data):
    names = [poi['name'] for poi in poi_data]
    return names

def main():
    input_file = 'POI/beijing_poi_attractions.json'
    output_file = 'POI/attractions_names.json'

    # Read the JSON data from the input file
    poi_data = read_json_file(input_file)

    # Extract names from the data
    names = extract_names(poi_data)

    # Write names to a new JSON file
    write_json_file(names, output_file)
    print(f'Saved {len(names)} restaurant names to {output_file}')

if __name__ == '__main__':
    main()
