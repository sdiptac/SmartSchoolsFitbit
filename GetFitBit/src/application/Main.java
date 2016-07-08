package application;

public class Main {

	public static void main(String[] args) {
		while(true){
			try{
				Connector.connect();
				String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0UEJYQlEiLCJhdWQiOiIyMjdWNTMiLCJpc3MiOiJGaXRiaXQiLCJ0eXAiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZXMiOiJ3aHIgd251dCB3cHJvIHdzbGUgd3dlaSB3c29jIHdzZXQgd2FjdCB3bG9jIiwiZXhwIjoxNDk5NTIzOTI4LCJpYXQiOjE0Njc5ODc5Mjh9.W74YpdpGl9Nw66uwTWeZARtAE_3cR6UNNawtKKbKXOs";
				String id = "4PBXBQ";
				URLReader.getUserProfile(id,token);
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}
	}

}
