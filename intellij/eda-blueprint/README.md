# Blueprint for EDA (event driven architecture)
***using wikimedia streams as example***

- consists only of simple producer/consumer
- read JSONs from `https://stream.wikimedia.org/v2/stream/recentchange` and produce an event, write it to a topic
- consumer the event and persist