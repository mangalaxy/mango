import React from 'react'

class App extends React.Component {

  intervalID = 0;
  state = {};

  componentDidMount() {
    this.intervalID = setInterval(this.hello, 1000);
  }

  componentWillUnmount() {
    clearInterval(this.intervalID);
  }

  hello = () => {
    fetch('/api/hello')
        .then(response => response.text())
        .then(message => {
          this.setState({message: message});
        });
  };

  render() {
    return (
        <div>
          <header>
            <h3>{this.state.message}</h3>
          </header>
        </div>
    )
  }
}

export default App;
