import pickle
import sys
import os

script_dir = os.path.dirname(os.path.realpath(__file__))
model_path = os.path.join(script_dir, 'model.pkl')

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python model.py '<input_string>'")
        sys.exit(1)

    data = sys.argv[1]

    with open(model_path, 'rb') as file:
        model = pickle.load(file)

    try:
        # Assume the model can handle the input string directly
        result = model.predict([data])
        print(result[0]['label'])
    except Exception as e:
        print(f"An error occurred during prediction: {e}")
        sys.exit(1)
