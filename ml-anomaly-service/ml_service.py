from fastapi import FastAPI
from pydantic import BaseModel
import joblib
import numpy as np

model = joblib.load("anomaly_detection.joblib")
scaler = joblib.load("scaler.joblib")

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

app = FastAPI()

class LogInput(BaseModel):
    cpu_utilization: float
    memory_usage: float
    disk_io: float
    network_latency: float
    process_count: int
    thread_count: int
    context_switches: int
    cache_miss_rate: float
    temperature: float
    power_consumption: float
    uptime: int

@app.post("/predict")
def predict(data: LogInput):

    X = np.array([[getattr(data, f) for f in FEATURES]])
    X_scaled = scaler.transform(X)

    prediction = model.predict(X_scaled)[0]
    score = model.decision_function(X_scaled)[0]

    z_scores = np.abs(X_scaled[0])
    top_indices = z_scores.argsort()[-3:][::-1]

    likely_causes = [
        {
            "metric": FEATURES[i],
            "severity": float(round(z_scores[i], 2))
        }
        for i in top_indices
    ]

    return {
        # ✅ convert numpy.bool_ → bool
        "anomaly": bool(prediction == -1),

        # ✅ convert numpy.float → float
        "anomaly_score": float(round(score, 4)),

        "severity_level": (
            "CRITICAL" if score < -0.15 else
            "MEDIUM" if score < -0.08 else
            "LOW"
        ),

        "likely_causes": likely_causes
    }
