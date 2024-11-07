import pygraphviz as pgv
import pandas as pd
import sys

if len(sys.argv) != 2:
    print("Usage: python script_name.py <model_name>")
    sys.exit(1)

# Load the graph from the DOT file
dot_file = sys.argv[1]
try:
    graph = pgv.AGraph(dot_file)
    
    # Prepare data for the 4-column format
    edge_data = []
    for edge in graph.edges():
        ei = edge[0]  # starting node
        ej = edge[1]  # ending node
        label = edge.attr.get("label", "None")  # Get the label attribute
    
        # Split label into input and output if it contains "/"
        if "/" in label:
            input_label, output_label = label.split("/", 1)
        else:
            input_label = label
            output_label = "None"  # Default to None if there's no output part

        edge_data.append([ei, ej, input_label, output_label])
    
    # Create a DataFrame for easy viewing and manipulation
    df = pd.DataFrame(edge_data, columns=["ei", "ej", "input", "output"])
    
    # Display the DataFrame
    print(df.to_string(index=False, header=False))
except Exception as e:
    print(f"Error processing the DOT file '{dot_file}': {e}")
