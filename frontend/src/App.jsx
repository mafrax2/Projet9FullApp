import logo from './logo.svg';
import './App.css';
import FindInputs from './components/FindInputs';
import PatientInfo from './components/PatientInfo';
import AddPatient from './components/AddPatient';

const App = () => {
  return (
    < div >
      <FindInputs> </FindInputs>
      <PatientInfo></PatientInfo>
      <AddPatient></AddPatient>
    </div>
  );
}

export default App;