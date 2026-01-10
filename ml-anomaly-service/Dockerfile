FROM python:3.11-slim

WORKDIR /app

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY ml_service.py .
COPY anomaly_detection.joblib .
COPY scaler.joblib .

EXPOSE 5000

CMD ["uvicorn", "ml_service:app", "--host", "0.0.0.0", "--port", "5000"]
