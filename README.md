# SentiMental Health

SentiMental Health is an online mental health blogging application where patients post journal entries, can reply to other patients entries, and have an assigned mental health professional.

## Features
- Users can register for a **patient** or **professional** account.
- Patients can post public or private journal entries.
- Patients have a progress graph of the last 7 days averaging their entry sentiment scores.
- Entry sentiment is evaluated using [DeepAI](https://deepai.org/machine-learning-model/sentiment-analysis).
- Professionals can be assigned to patients and view the patient's private entries and progress graphs.
- Patients and professionals can reply to entries.

## Preview
#### My Entries
![my entries](https://i.imgur.com/0xmM4LI.png)

#### Public Entries
![public entries](https://i.imgur.com/kZ3ptlR.png)

#### Entry View
![entry view](https://i.imgur.com/Ka3fb2t.png)

#### New Entry
![new entry](https://i.imgur.com/Otzk9Qb.png)

#### Progress Graph
![progress graph](https://i.imgur.com/OBMFvul.png)


## Potential improvements
- Validate and verify user emails after registration
- Verify professional identity through document verification
- Implement caching data through Redis
- Deleting entries/replies
- Patients having more than one assigned professional
- Replying to replies

## License
This project is licensed under the MIT License.
