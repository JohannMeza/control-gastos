import subprocess

try:
    output = subprocess.check_output(["git", "show", ":src/view/AhorrosView.form"], text=True)
    # Print lines around 1640 to 1670
    lines = output.splitlines()
    for idx in range(1630, min(1680, len(lines))):
        print(f"{idx+1}: {lines[idx]}")
except Exception as e:
    print("Error:", e)
