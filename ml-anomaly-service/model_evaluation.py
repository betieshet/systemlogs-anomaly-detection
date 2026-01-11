import pandas as pd
import numpy as np
from sklearn.metrics import classification_report, confusion_matrix
import joblib


model = joblib.load("anomaly_detection.joblib")
scaler = joblib.load("scaler.joblib")


df = pd.read_csv("<dataset.csv>")

FEATURES = [
    "cpu_utilization",
    "memory_usage",
    "disk_io",
    "network_latency",
    "process_count",
    "thread_count",
    "context_switches",
    "cache_miss_rate",
    "temperature",
    "power_consumption",
    "uptime"
]

df["status_clean"] = (
    df["status"]
    .astype(str)
    .str.strip()
    .str.upper()
)

y_true = df["status_clean"].apply(
    lambda x: 1 if x in ["ANOMALY", "1", "TRUE"] else 0
)

X_scaled = scaler.transform(df[FEATURES])

# Model predictions
y_pred = model.predict(X_scaled)
y_pred = np.where(y_pred == -1, 1, 0)

print("Confusion Matrix:")
print(confusion_matrix(y_true, y_pred))

print("\nClassification Report:")
print(classification_report(y_true, y_pred))
