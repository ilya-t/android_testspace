def ask(_question):
    _question.ask()
    input = raw_input().lower()
    question = _question.answer(input)
    if not question is None:
        ask(question)
pass

class Question:
    def __init__(self, _prompt, _answers=None, _parse_answer=None):
        self.prompt = _prompt
        self.answers = _answers
        self.parse_answer = _parse_answer
        pass

    def ask(self):
        print (self.prompt)

    def answer(self, _input):
        if not self.answers or not dict.__contains__(self.answers, _input):
            if not self.parse_answer:
                self.ask()
                return self.answer(raw_input().lower())
            return self.parse_answer(_input)
        return self.answers[_input]()