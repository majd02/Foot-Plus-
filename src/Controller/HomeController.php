<?php
namespace App\Controller;

use App\Entity\Reservation;
use App\Entity\Stade;
use App\Entity\StadeSearch;
use App\Form\ReservationType;
use App\Form\StadeSearchType;
use App\Repository\StadeRepository;

use BotMan\BotMan\BotMan;
use BotMan\BotMan\BotManFactory;
use BotMan\BotMan\Drivers\DriverManager;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Twig\Environment;


class HomeController extends AbstractController {




    /**
     * @Route ("/" , name="home")
     * @param StadeRepository $repository
     * @return Response
     */

    public function index(StadeRepository $repository,Request $request):Response
    {
        $Stades= $repository->findAll();

        return $this->render('pages/home.html.twig',[
            'Stades'=>$Stades
        ]);
    }

    /**
     * @Route ("/stade/{slug}-{idStade}" , name="show")
     * @param $slug
     * @param $idStade
     * @param StadeRepository $repository
     * @return Response
     */

    public function show($slug, $idStade, $repository,Request $request) : Response
    {
        $reservation = new Reservation();
        $reservation->setIdStade($idStade);
        $form1 = $this->createForm(ReservationType::class, $reservation);
        $form1->handleRequest($request);
        DriverManager::loadDriver(\BotMan\Drivers\Web\WebDriver::class);

        // Configuration for the BotMan WebDriver
        $config = [];

        // Create BotMan instance
        $botman = BotManFactory::create($config);

        if ($form1->isSubmitted() && $form1->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($reservation);
            $entityManager->flush();
            $this->addFlash(
                'info',
                'added successfully!'
            );
            function (BotMan $bot) {
                $bot->reply('Hello!');
            };

        }
            $Stades = $repository->find($idStade);

        return $this->render('stade/show.html.twig', [
            'Stades' => $Stades,

            'form' => $form1->createView()
        ]);


    }
    /**
     *
     * @Route("/search", name="ajax_search",methods={"GET"})
     *
     */
    public function searchAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $stades=$em->getRepository('App:Stade')->findEntitiesByString($requestString);
        if(!$stades){
            $result['stades']['error']="Not Found :(";
        }
        else {
            $result['stades']=$this->getRealEntities($stades);
        }
        return new Response(json_encode($result));
        $Stades= $repository->findAll();
        return $this->render('pages/home.html.twig', [
            'Stades' => $Stades,

        ]);
    }
    public function getRealEntities($stades){
        foreach ($stades as $stades){
            $realEntities[$stades->getIdStade()] = [$stades->getNom()];
        }
        return $realEntities;
    }


}